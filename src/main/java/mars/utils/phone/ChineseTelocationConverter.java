package mars.utils.phone;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ChineseTelocationConverter {
    private static final String TAG = "ChineseTelocation";

    public static final int UNIQUE_ID_NONE = 0;

    public static final int LOCATION_KIND = 0;
    public static final int LOCATION_INDEX = 0;
    public static final int AREACODE_INDEX = 1;

    private static final String EMPTY = "";
    private static final String DATA_ASSET_NAME = "telocation-10.idf";

    private static ChineseTelocationConverter sInstance = new ChineseTelocationConverter();

    private HashMap<String, String> mAreaCodeTelocations = new HashMap<String, String>();

    private DirectIndexedFile.Reader mDensityIndexFileReader;

    private ChineseTelocationConverter() {
        String dataPath = "/home/mi/下载/"+ DATA_ASSET_NAME;
        File file = new File(dataPath);
        if (dataPath != null && file.exists()) {
            try {
                mDensityIndexFileReader = DirectIndexedFile.open(dataPath);
               // Log.v(TAG, "Read Telocation from " + dataPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (mDensityIndexFileReader == null) {
            try {
                Resource resource0626 = new ClassPathResource("/" + DATA_ASSET_NAME);
                //AssetManager mAssets = PackageConstants.getCurrentApplication().getResources().getAssets();
                InputStream is = resource0626.getInputStream();
                mDensityIndexFileReader = DirectIndexedFile.open(is);
                //Log.v(TAG, "Read Telocation from assets");
            } catch (IOException e) {
                System.currentTimeMillis();
                //Log.w(TAG, "Can't read " + DATA_ASSET_NAME + ", NO mobile telocation supported!");
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (mDensityIndexFileReader != null) {
            mDensityIndexFileReader.close();
        }

        super.finalize();
    }

    public static ChineseTelocationConverter getInstance() {
        return sInstance;
    }

    /**
     * When telocation was updated, we should reload the instance to get the right Version.
     * @hide
     */
    public static void reloadInstance() {
        sInstance = new ChineseTelocationConverter();
    }

    public int getUniqId(CharSequence cs, int start, int length, boolean enableMobile) {
        if (length > 0 && cs.charAt(start) == '0') {
            ++start;
            --length;
        }
        if (length <= 1) {
            return UNIQUE_ID_NONE;
        }

        int index;
        switch (cs.charAt(start)) {
            case '0':
                break;
            case '1':
                if (cs.charAt(start + 1) == '0') {
                    return 10;
                } else if (enableMobile && length > 6) {
                    index = 1000000
                            + (cs.charAt(start + 1) - '0') * 100000 + (cs.charAt(start + 2) - '0') * 10000
                            + (cs.charAt(start + 3) - '0') * 1000 + (cs.charAt(start + 4) - '0') * 100
                            + (cs.charAt(start + 5) - '0') * 10 + (cs.charAt(start + 6) - '0');
                    if (index == 1380013 && length > 10
                            && cs.charAt(start + 7) == '8' && cs.charAt(start + 8) == '0'
                            && cs.charAt(start + 9) == '0' && cs.charAt(start + 10) == '0') {
                        // 13800138000 has no location.
                        return UNIQUE_ID_NONE;
                    }
                    return index;
                }
                break;
            case '2':
                index = 20 + (cs.charAt(start + 1) - '0');
                return index;
            default:
                if (length > 2) {
                    index = ((cs.charAt(start) - '0') * 10 + (cs.charAt(start + 1) - '0'))
                            * 10 + (cs.charAt(start + 2) - '0');
                    return index;
                }
        }
        return UNIQUE_ID_NONE;
    }

    public String getLocation(CharSequence cs, int start, int length, boolean withAreaCode) {
        // If mDensityIndexFileReader is not initialized correctly, return "";
        if (mDensityIndexFileReader == null) {
            return EMPTY; // Use "" to avoid NPE
        }

        // If without areaCode , return null;
        if (!withAreaCode) {
            return EMPTY; // Use "" to avoid NPE
        }

        int id = getUniqId(cs, start, length, true);

        if (id <= 0) {
            return EMPTY; // Use "" to avoid NPE
        }

        return (String) mDensityIndexFileReader.get(LOCATION_KIND, id, LOCATION_INDEX);
    }

    public String getAreaCode(CharSequence cs, int start, int length) {
        // Use "" to avoid NPE
        if (mDensityIndexFileReader == null) {
            return EMPTY;
        }
        int id = getUniqId(cs, start, length, true);
        return (String) mDensityIndexFileReader.get(LOCATION_KIND, id, AREACODE_INDEX);
    }

    public String parseAreaCode(CharSequence cs, int start, int length) {
        // Use "" to avoid NPE
        if (mDensityIndexFileReader == null) {
            return EMPTY;
        }
        int id = getUniqId(cs, start, length, false);
        return (String) mDensityIndexFileReader.get(LOCATION_KIND, id, AREACODE_INDEX);
    }

    /**
     * Get area code according to address.
     *
     * @param context
     * @param address
     */
   /*public String getAreaCode(Address address) {
        if (address == null || mDensityIndexFileReader == null) {
            return EMPTY;
        }

        if (mAreaCodeTelocations.size() == 0) {
            synchronized (mAreaCodeTelocations) {
                if (mAreaCodeTelocations.size() == 0) {
                    for (int id = 0; id < 1000; id++) {
                        String location = (String) mDensityIndexFileReader.get(LOCATION_KIND, id, LOCATION_INDEX);
                        if (!TextUtils.isEmpty(location)) {
                            mAreaCodeTelocations.put(String.valueOf(id), location);
                        }
                    }
                }
            }
        }

        String adminArea = address.getAdminArea();
        String locality = address.getLocality();
        //Log.d(TAG, "adminArea:" + adminArea + " locality:" + locality);
        if (!TextUtils.isEmpty(adminArea) && !TextUtils.isEmpty(locality)) {
            // 将地址信息标准化，去掉其中的省、市、县、区信息
            adminArea = adminArea.replace("省", "");
            adminArea = adminArea.replace("市", "");
            locality = locality.replace("市", "");
            locality = locality.replace("区", "");
            locality = locality.replace("县", "");
            for (Map.Entry<String, String> entry : mAreaCodeTelocations.entrySet()) {
                String normalizedAddress = entry.getValue();
                if (normalizedAddress.startsWith(adminArea) &&
                        normalizedAddress.contains(locality)) {
                    return entry.getKey();
                }
            }
        }
        return EMPTY;
    }*/

    public int getVersion() {
        return mDensityIndexFileReader.getDataVersion();
    }
}
