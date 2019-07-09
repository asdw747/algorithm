package mars.finance;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import mars.finance.model.ExcelField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
public class ExcelTool {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final int TITLE_STYLE_TYPE = 1;
    public static final int DATA_STYLE_TYPE = 2;
    public static final int HEADER_STYLE_TYPE = 3;

    private HSSFWorkbook workbook;
    private Map<Integer, CellStyle> styles;

    /**
     * 生成sheet
     */
    public static <T> ExcelTool generate(Class<T> clazz, List<T> records) throws Exception {
        return generate(clazz, records, null, new HashMap<>(), 0);
    }

    /**
     * 生成sheet，指定sheet名称
     */
    public static <T> ExcelTool generate(Class<T> clazz, List<T> records, String sheetName) throws Exception {
        return generate(clazz, records, sheetName, new HashMap<>(), 0);
    }

    /**
     * 生成sheet，指定sheet名称，指定栏位名称
     */
    public static <T> ExcelTool generate(Class<T> clazz, List<T> records, String sheetName, Map<Integer, String> fieldNames) throws Exception {
        return generate(clazz, records, sheetName, fieldNames, 0);
    }

    /**
     * 生成sheet，指定sheet名称，指定栏位名称，并在最上方留空行
     *
     * @param headerHeight 表头需要空出的行数
     */
    public static <T> ExcelTool generate(Class<T> clazz, List<T> records, String sheetName, Map<Integer, String> fieldNames, int headerHeight) throws Exception {
        Map<Integer, Object> fieldRow = getFieldRow(clazz, fieldNames);

        HSSFWorkbook wb = new HSSFWorkbook();
        ExcelTool excelHandler = new ExcelTool();
        excelHandler.setWorkbook(wb);
        excelHandler.createStyles();

        HSSFSheet sheet = wb.createSheet();
        if (StringUtils.isNotBlank(sheetName)) {
            wb.setSheetName(0, sheetName);
        }

        Row sentTitleRow = sheet.createRow(headerHeight);
        fillRow(sentTitleRow, fieldRow, excelHandler.getStyle(TITLE_STYLE_TYPE));
        if (CollectionUtils.isNotEmpty(records)) {
            for (int i = 0; i < records.size(); i++) {
                Row row = sheet.createRow(i + headerHeight + 1);
                fillRow(row, getRecordRow(records.get(i)), excelHandler.getStyle(DATA_STYLE_TYPE));
            }
        }

        autoColumnWidth(sheet, clazz);
        return excelHandler;
    }


    public static ExcelTool generate(String sheetName, JSONObject jsonObject, int headerHeight) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        ExcelTool excelHandler = new ExcelTool();
        excelHandler.setWorkbook(wb);
        excelHandler.createStyles();

        HSSFSheet sheet = wb.createSheet();
        if (StringUtils.isNotBlank(sheetName)) {
            wb.setSheetName(0, sheetName);
        }

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONArray columns = jsonArray.getJSONObject(0).getJSONArray("columns");
        JSONArray rows = jsonArray.getJSONObject(0).getJSONArray("rows");

        Map<Integer, Object> fieldRow = new HashMap<>();

        for (int i = 0; i < columns.size(); i++) {
            JSONObject col = columns.getJSONObject(i);
            fieldRow.put(i, col.get("title"));
            sheet.setColumnWidth(i, 20 * 256);
        }

        Row sentTitleRow = sheet.createRow(headerHeight);
        fillRow(sentTitleRow, fieldRow, excelHandler.getStyle(TITLE_STYLE_TYPE));
        if (CollectionUtils.isNotEmpty(rows)) {
            excelHandler.appendRow(headerHeight, sheet, columns, rows, excelHandler.getStyle(DATA_STYLE_TYPE));
        }

        return excelHandler;
    }

    public  ExcelTool appendSheet(String sheetName, JSONObject jsonObject, int headerHeight) throws Exception {

        HSSFSheet sheet = workbook.createSheet();

        if (StringUtils.isNotBlank(sheetName)) {
            int sheetIndex = workbook.getSheetIndex(sheet.getSheetName());
            workbook.setSheetName(sheetIndex, sheetName);
        }

        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONArray columns = jsonArray.getJSONObject(0).getJSONArray("columns");
        JSONArray rows = jsonArray.getJSONObject(0).getJSONArray("rows");

        Map<Integer, Object> fieldRow = new HashMap<>();

        for (int i = 0; i < columns.size(); i++) {
            JSONObject col = columns.getJSONObject(i);
            fieldRow.put(i, col.get("title"));
            sheet.setColumnWidth(i, 20 * 256);
        }

        Row sentTitleRow = sheet.createRow(headerHeight);
        fillRow(sentTitleRow, fieldRow, getStyle(TITLE_STYLE_TYPE));
        if (CollectionUtils.isNotEmpty(rows)) {
            appendRow(headerHeight, sheet, columns, rows, getStyle(DATA_STYLE_TYPE));
        }

        return this;
    }

    private static void appendRow(int headerHeight, HSSFSheet sheet, JSONArray columns, JSONArray rows, CellStyle style) {
        try {
            for (int i = 0; i < rows.size(); i++) {
                JSONObject object = rows.getJSONObject(i);
                Row row = sheet.createRow(i + headerHeight + 1);
                Map<Integer, Object> rowData = new HashMap<>();
                for (int j = 0; j < columns.size(); j++) {
                    JSONObject col = columns.getJSONObject(j);
                    String field = col.getString("field");
                    String v = object.getString(field);
                    if (StringUtils.isNotBlank(v)) {
                        rowData.put(j, v);
                    }
                }
                fillRow(row, rowData, style);
            }

        } catch (Exception e) {
        }
    }


    /**
     * 追加sheet
     */
    public <T> ExcelTool appendSheet(Class<T> clazz, List<T> records) throws Exception {
        appendSheet(clazz, records, null, new HashMap<>(), 0);
        return this;
    }

    /**
     * 追加sheet，指定sheet名称
     */
    public <T> ExcelTool appendSheet(Class<T> clazz, List<T> records, String sheetName) throws Exception {
        appendSheet(clazz, records, sheetName, new HashMap<>(), 0);
        return this;
    }

    /**
     * 追加sheet，指定sheet名称，指定栏位名称
     */
    public <T> ExcelTool appendSheet(Class<T> clazz, List<T> records, String sheetName, Map<Integer, String> fieldNames) throws Exception {
        appendSheet(clazz, records, sheetName, fieldNames, 0);
        return this;
    }

    /**
     * 追加sheet，指定sheet名称，指定栏位名称，并在最上方留空行
     *
     * @param headerHeight 表头需要空出的行数
     */
    public <T> ExcelTool appendSheet(Class<T> clazz, List<T> records, String sheetName, Map<Integer, String> fieldNames, int headerHeight) throws Exception {
        Map<Integer, Object> fieldRow = getFieldRow(clazz, fieldNames);

        HSSFSheet sheet = workbook.createSheet();

        if (StringUtils.isNotBlank(sheetName)) {
            int sheetIndex = workbook.getSheetIndex(sheet.getSheetName());
            workbook.setSheetName(sheetIndex, sheetName);
        }

        Row titleRow = sheet.createRow(headerHeight);
        fillRow(titleRow, fieldRow, getStyle(TITLE_STYLE_TYPE));
        if (CollectionUtils.isNotEmpty(records)) {
            for (int i = 0; i < records.size(); i++) {
                Row row = sheet.createRow(i + headerHeight + 1);
                fillRow(row, getRecordRow(records.get(i)), getStyle(DATA_STYLE_TYPE));
            }
        }

        autoColumnWidth(sheet, clazz);
        return this;
    }

    public void export(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        final String userAgent = request.getHeader("USER-AGENT");
        if (StringUtils.contains(userAgent, "MSIE")) {//IE浏览器
            fileName = URLEncoder.encode(fileName, "UTF8");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {//google,火狐浏览器
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF8");//其他浏览器
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        this.write(response.getOutputStream());
    }

    /**
     * 输出Excel文件
     */
    public void write(OutputStream outputStream) throws IOException {
        workbook.write(outputStream);
    }

    /**
     * 读取Excel文件
     */
    public static ExcelTool read(InputStream inputStream) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        ExcelTool excelHandler = new ExcelTool();
        excelHandler.setWorkbook(wb);
        return excelHandler;
    }

    /**
     * 解析sheet
     *
     * @param sheetIndex sheet标号（0起算）
     * @param startRow   待解析的起始数据行的行号（不含标题行，0起算）
     * @param endRow     待解析的截止数据行的行号（0起算）
     * @param clazz      仅解析使用@ExcelField注解的成员变量
     */
    public <T> List<T> parse(int sheetIndex, int startRow, int endRow, Class<T> clazz) throws Exception {
        List<T> dataList = new ArrayList<>(endRow - startRow + 1);

        HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            T data = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }

                ExcelField excelField = f.getAnnotation(ExcelField.class);
                if (excelField != null) {
                    Cell cell = row.getCell(excelField.column());

                    Object value;
                    switch (getValueType(f.getType())) {
                        case NUMBER:
                            value = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case DATE:
                            value = cell.getDateCellValue();
                            break;
                        default:
                            value = cell.getStringCellValue();
                    }
                    setFieldValue(data, f.getName(), value);
                }
            }
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * 获取常用的格式（栏位、数据、题头等）
     *
     * @param styleType 见本类常量
     * @return
     */
    public CellStyle getStyle(int styleType) {
        return styles.get(styleType);
    }

    private static Map<Integer, Object> getFieldRow(Class clazz, Map<Integer, String> fieldNames) throws Exception {
        Map<Integer, Object> fieldRow = new HashMap<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            ExcelField excelField = f.getAnnotation(ExcelField.class);
            if (excelField != null) {
                fieldRow.put(excelField.column(), excelField.name());
            }
        }

        fieldNames.forEach(fieldRow::put);

        return fieldRow;
    }

    private static Map<Integer, Object> getRecordRow(Object record) throws Exception {
        Map<Integer, Object> rowData = new HashMap<>();

        if (record == null) {
            return rowData;
        }

        Field[] fields = record.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            ExcelField excelField = f.getAnnotation(ExcelField.class);
            if (excelField != null) {
                if (f.get(record) != null) {
                    rowData.put(excelField.column(), f.get(record));
                }
            }
        }
        return rowData;
    }

    private static List<Pair<Integer, String>> autoColumnWidth(HSSFSheet sheet, Class clazz) throws Exception {
        List<Pair<Integer, String>> fieldRow = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            ExcelField excelField = f.getAnnotation(ExcelField.class);
            if (excelField != null) {
                sheet.setColumnWidth(excelField.column(), 20 * 256);
            }
        }

        return fieldRow;
    }

    private static void fillRow(Row row, Map<Integer, Object> rowData, CellStyle cellStyle) {
        rowData.forEach((index, data) -> {
            Cell cell = row.createCell(index);
            cell.setCellStyle(cellStyle);

            switch (getValueType(data)) {
                case NUMBER:
                    cell.setCellValue(Double.parseDouble(data.toString()));
                    break;
                case BOOLEAN:
                    cell.setCellValue((boolean) data);
                    break;
                case DATE:
                    cell.setCellValue((Date) data);
                    break;
                default:
                    cell.setCellValue(data.toString());
            }
        });
    }

    private static void setFieldValue(Object object, String fieldName, Object value) throws IllegalAccessException {
        Field field = ReflectionUtils.findField(object.getClass(), fieldName);

        if (field == null) {
            return;
        }
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        field.set(object, value);
    }

    private void createStyles() {
        styles = new HashMap<>();

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dataStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setBorderTop(CellStyle.BORDER_THIN);
        dataStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = workbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        styles.put(DATA_STYLE_TYPE, dataStyle);

        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.cloneStyleFrom(dataStyle);
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleStyle.setFont(titleFont);
        styles.put(TITLE_STYLE_TYPE, titleStyle);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.cloneStyleFrom(dataStyle);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        styles.put(HEADER_STYLE_TYPE, headerStyle);
    }

    private static ValueType getValueType(Object object) {
        return getValueType(object.getClass());
    }

    private static ValueType getValueType(Class clazz) {
        if (clazz == byte.class || clazz == Byte.class ||
                clazz == short.class || clazz == Short.class ||
                clazz == int.class || clazz == Integer.class ||
                clazz == long.class || clazz == Long.class ||
                clazz == float.class || clazz == Float.class ||
                clazz == double.class || clazz == Double.class) {
            return ValueType.NUMBER;
        }
        if (clazz == boolean.class || clazz == Boolean.class) {
            return ValueType.BOOLEAN;
        }
        if (clazz == Date.class) {
            return ValueType.DATE;
        }
        if (clazz == String.class) {
            return ValueType.STRING;
        }
        return ValueType.ELSE;
    }

    enum ValueType {
        NUMBER(1, "数字"),
        BOOLEAN(2, "布尔"),
        DATE(3, "日期"),
        STRING(4, "字符串"),
        ELSE(5, "其他");

        @Getter
        private final int value;
        @Getter
        private final String desc;

        ValueType(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }
    }

}
