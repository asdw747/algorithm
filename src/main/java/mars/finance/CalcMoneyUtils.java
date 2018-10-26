//package mars.finance;
//
//import shade.org.apache.commons.httpclient.util.DateUtil;
//
//import java.math.BigDecimal;
//import java.math.MathContext;
//import java.text.ParseException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CalcMoneyUtils {
//    private static BigDecimal amount_0 = new BigDecimal("0.00");
////	等额本息还款法:
////		每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
////		每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
////		每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
////		总利息=还款月数×每月月供额-贷款本金
////	等额本金还款法:
////		每月月供额=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
////		每月应还本金=贷款本金÷还款月数
////		每月应还利息=剩余本金×月利率=(贷款本金-已归还本金累计额)×月利率
////		每月月供递减额=每月应还本金×月利率=贷款本金÷还款月数×月利率
////		总利息=还款月数×(总贷款额×月利率-月利率×(总贷款额÷还款月数)*(还款月数-1)÷2+总贷款额÷还款月数)
////		月利率=年利率÷12
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还本金和利息
//     * <p>
//     * 公式：每月偿还本息=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
//     *
//     * @param invest     总借款额（贷款本金）
//     * @param monthRate   月利率（单位%）
//     * @param totalmonth 还款总月数
//     * @return 每月偿还本金和利息, 四舍五入，取小数点最后两位
//     * BigDecimal//加法add //减法subtract //乘法multiply//除法divide
//     * double d = Math.pow(1d + rate , periods);// (1 + 利率)^期数
//    double resultMonthly = (capital * rate * d) / (d - 1);
//     */
//    public static BigDecimal getPerMonthPrincipalInterest(BigDecimal invest, BigDecimal monthRate, int totalmonth) {
////    	BigDecimal monthRate = yearRate.divide(new BigDecimal("100")).divide(new BigDecimal(12), MathContext.DECIMAL64);
//        if(monthRate.compareTo(amount_0) <= 0) return invest;
//        BigDecimal lv = new BigDecimal(Math.pow(new BigDecimal("1").add(monthRate).doubleValue(), totalmonth), MathContext.DECIMAL64);
//        BigDecimal monthIncome = invest.multiply(monthRate).multiply(lv)
//                .divide(lv.subtract(new BigDecimal("1")),2,BigDecimal.ROUND_HALF_UP);
//        return monthIncome;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还本金
//     * 公式：每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
//     * @param invest     总借款额（贷款本金）
//     * @param monthRate   月利率
//     * @param totalmonth 还款总月数
//     * @return 每月偿还本金
//     */
//    public static Map<Integer, BigDecimal> getPerMonthPrincipal(BigDecimal invest, BigDecimal monthRate, int totalmonth) {
////    	BigDecimal monthRate = yearRate.divide(new BigDecimal("100")).divide(new BigDecimal(12), MathContext.DECIMAL64);
//        BigDecimal monthIncome = getPerMonthPrincipalInterest(invest, monthRate, totalmonth);
//        Map<Integer, BigDecimal> mapInterest = getPerMonthInterest(invest, monthRate, totalmonth);
//        Map<Integer, BigDecimal> mapCaptial = new HashMap<Integer, BigDecimal>();
//        BigDecimal sumCaptial = amount_0;//除最后一期所有本金之和
//        for (int order = 1; order < totalmonth+1; order++) {
//            BigDecimal monthCaptial = monthIncome.subtract(mapInterest.get(order));
//            if(order == totalmonth){
//                monthCaptial = invest.subtract(sumCaptial);
//            }else{
//                sumCaptial = sumCaptial.add(monthCaptial);
//            }
//            mapCaptial.put(order, monthCaptial);
//        }
//        return mapCaptial;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的每月偿还利息
//     *
//     * 公式：每月偿还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
//     * 本公式中为月供-当期本金 = 当期利息
//     * @param invest     总借款额（贷款本金）
//     * @param monthRate  月利率
//     * @param totalmonth 还款总月数
//     * @return 每月偿还利息
//     */
//    public static Map<Integer, BigDecimal> getPerMonthInterest(BigDecimal invest, BigDecimal monthRate, int totalmonth) {
//        BigDecimal monthIncome = getPerMonthPrincipalInterest(invest, monthRate, totalmonth);
//        Map<Integer, BigDecimal> mapInterest = new HashMap<Integer, BigDecimal>();
//        BigDecimal sumInterest = amount_0;//除最后一期所有利息之和
//        for (int order = 1; order < totalmonth+1; order++) {
//            BigDecimal monthInterest = invest.multiply(monthRate)
//                    .multiply(
//                            new BigDecimal(Math.pow(new BigDecimal("1").add(monthRate).doubleValue(), totalmonth), MathContext.DECIMAL64)
//                                    .subtract(new BigDecimal(Math.pow(new BigDecimal("1").add(monthRate).doubleValue(), order-1), MathContext.DECIMAL64))
//                    ).divide(new BigDecimal(Math.pow(new BigDecimal("1").add(monthRate).doubleValue(), totalmonth), MathContext.DECIMAL64)
//                            .subtract(new BigDecimal("1")),2,BigDecimal.ROUND_HALF_UP);
//            if(order == totalmonth){
//                mapInterest.put(order, monthIncome.multiply(new BigDecimal(totalmonth)).subtract(invest).subtract(sumInterest));
//            }else{
//                mapInterest.put(order, monthInterest);
//                sumInterest = sumInterest.add(monthInterest);
//            }
//        }
//        return mapInterest;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的当期之后的剩余本金之和
//     */
//    public static BigDecimal getRemainCaptial(Map<Integer, BigDecimal> preCaptial, int order) {
//        BigDecimal remainCaptial = amount_0;
//        for (Map.Entry<Integer, BigDecimal> entry : preCaptial.entrySet()) {
//            if (entry.getKey() > order) remainCaptial = remainCaptial.add(entry.getValue());
//        }
//        return remainCaptial;
//    }
//
//    /**
//     * 等额本息计算获取还款方式为等额本息的当期之后的剩余利息结合
//     */
//    public static BigDecimal getRemainInterest(Map<Integer, BigDecimal> preInterest, int order) {
//        BigDecimal remainInterest = amount_0;
//        for (Map.Entry<Integer, BigDecimal> entry : preInterest.entrySet()) {
//            if (entry.getKey() > order) remainInterest = remainInterest.add(entry.getValue());
//        }
//        return remainInterest;
//    }
//
//    /**
//     * 计算等额本息分润之后剩余的所有未还总金额之和
//     *
//     */
//    public static BigDecimal getAllMoney(List<BillItems> blist, int orderNo) {
//        BigDecimal remainAllSum = amount_0;
//        for (BillItems billItems : blist) {
//            if (billItems.getOrder() >= orderNo) remainAllSum = remainAllSum.add(billItems.getCapital().add(billItems.getInterest()));
//        }
//        return remainAllSum;
//    }
//
//    /**
//     * 计算逾期金额
//     * ygAmount 月供金额
//     * @throws ParseException
//     */
//    public static BigDecimal getYQMoney(int yqDays,BigDecimal ygAmount) {
//        return ygAmount.multiply(new BigDecimal(yqDays)).multiply(new BigDecimal("0.0005")).setScale(2, BigDecimal.ROUND_HALF_UP);
//    }
//    public static BigDecimal getYQMoney(Date yhDate, Date shDate, BigDecimal ygAmount) throws ParseException {
//        //逾期天数计算
//        int yqDays = DateUtil.daysBetween(yhDate, shDate);
//        return getYQMoney(yqDays, ygAmount);
//    }
//
//    /**
//     * 金融机构rate 变成小数
//     */
//    public static BigDecimal instRate2Bigrate(BigDecimal instRate){
//        return instRate.divide(new BigDecimal("100")).setScale(10);
//    }
//
//    /**
//     * 将订单类型的利率转成年利率
//     * 1：周 2：月 3：半月 4：日
//     */
//    public static BigDecimal activeRateToMonthRate(BigDecimal activeRate, int cycle,Integer lvDays) {
//        String orderType = orderTypeToString(cycle);
//        Integer billDays = billDays(cycle);
//        String rate = String.valueOf(instRate2Bigrate(activeRate));
//        //计费利率
//        String jfRate = BillsUtil.calculateRate(rate,orderType,billDays, lvDays, 10);
//        return new BigDecimal(jfRate);
//    }
//
//    /**
//     * 将订单类型转换为账单周期
//     * 1：周 2：月 3：半月 4：日
//     */
//    public static Integer billDays(int cycle) {
//        //周订单
//        if(cycle == 1) return 7;
//        //月订单
//        if(cycle == 2) return 30;
//        //半月订单
//        if(cycle == 3) return 15;
//        //按日计息订单
//        if(cycle == 4) return 1;
//        return 0;
//    }
//    /**
//     * 将订单类型转换为计费的类型
//     */
//    public static String orderTypeToString(Integer cycle){
//        //周订单
//        if(cycle == 1) return "WEEK";
//        //月订单
//        if(cycle == 2) return "MONTH";
//        //半月订单
//        if(cycle == 3) return "HALFMONTH";
//        //其他账期类型为any,具体由账期决定
//        return "ANY";
//    }
//
//    public static void main(String[] args) {
//        //System.out.println(getPerMonthPrincipalInterestByRate_0(new BigDecimal(100),3));
//        //System.out.println(getYQMoney(31,new BigDecimal("34")));
//        BigDecimal bjAmount = new BigDecimal("10000");
//        //将订单类型的利率转成年利率 1：周 2：月 3：半月
//        BigDecimal yearRate = activeRateToMonthRate(EnumInstitution.QDJC01.rate, 1,EnumInstitution.QDJC01.lvDays);
//        System.out.println(yearRate);
//        int fenqi = 6;
//        //月供
//        BigDecimal ygAmount = getPerMonthPrincipalInterest(bjAmount, yearRate, fenqi);
//        System.out.println("月供："+ygAmount);
//        //每期本金
//        Map<Integer, BigDecimal> preCaptial = getPerMonthPrincipal(bjAmount, yearRate, fenqi);
//        //每期利息
//        Map<Integer, BigDecimal> preInterest = getPerMonthInterest(bjAmount, yearRate, fenqi);
//        //测试
//        BigDecimal bjSum = amount_0;
//        BigDecimal lxSum = amount_0;
//        for (Map.Entry<Integer, BigDecimal> map : preCaptial.entrySet()) {
//            System.out.println("期次："+map.getKey()+"----月供："+map.getValue().add(preInterest.get(map.getKey()))+"----本金："+map.getValue()+"---利息："+preInterest.get(map.getKey())+"---剩余本金："+getRemainCaptial(preCaptial, map.getKey())+"---剩余利息："+getRemainCaptial(preInterest, map.getKey()));
//            bjSum = bjSum.add(map.getValue());
//            lxSum = lxSum.add(preInterest.get(map.getKey()));
//        }
//        System.out.println("贷款本金：" + bjAmount + "---总还款：" + bjSum.add(lxSum) + "---还款本金之和：" + bjSum + "----还款利息之和：" + lxSum);
//    }
//}
