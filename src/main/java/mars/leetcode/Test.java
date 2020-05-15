package mars.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.*;

public class Test {

    public static void main(String [] args) {

//        Map<Long, Model> map = new HashMap<>();
//        map.put(11L, new Model(11,11));
//        map.put(1L, new Model(1,1));
//        map.put(2L, new Model(2,2));
//        map.put(3L, new Model(3,3));
//
//        List<Model> models = new ArrayList<>(map.values());
//        System.out.println(map.values());

//        KeyPair keyPair = RSAUtils.generateRSAKeyPair(2048);
//        if (keyPair!=null) {
//            RSAUtils.printPublicKeyInfo(keyPair.getPublic());
//            RSAUtils.printPrivateKeyInfo(keyPair.getPrivate());
//        }

        System.out.println(getRandomString(24));

//        JSONObject jsonObject = JSON.parseObject("{\"ratingsUserGeneralScore\":2,\"ratingsUserMiConsumeScore\":4,\"ratingsUserCreditHistoryScore\":2,\"ratingsUserFinanceInterestScore\":8,\"ratingsUserLoanInterestScore\":5,\"ratingsUserFundInterestScore\":10,\"ratingsUserBankInterestScore\":4,\"ratingsUserSocialInterestScore\":7,\"ratingsUserShoppingInterestScore\":8,\"ratingsUserTravelInterestScore\":7,\"ratingsUserFoodInterestScore\":5,\"ratingsUserGameInterestScore\":2,\"ratingsUserSmartdeviceInterestScore\":6,\"ratingsFinanceAppInstallIndex\":2,\"ratingsLoanAppInstallIndex\":6,\"ratingsFundAppInstallIndex\":6,\"ratingsBankAppInstallIndex\":6,\"ratingsSocialAppInstallIndex\":6,\"ratingsTravelAppInstallIndex\":10,\"ratingsFoodAppInstallIndex\":9,\"ratingsGameAppInstallIndex\":4,\"ratingsShoppingAppInstallIndex\":8,\"behaviorConsumerPaySenceM12Model\":4,\"behaviorConsumerBookCountM12Model\":6,\"behaviorConsumerBookMoneyM12Model\":2,\"behaviorConsumerMiMallCountM12Model\":2,\"behaviorConsumerMiMallMoneyM12Model\":1,\"creditOverdueCountM1Model\":-1,\"creditOverdueCountM3Model\":-1,\"creditOverdueCountM6Model\":-1,\"creditOverdueCountM12Model\":-1,\"creditOverdueMoneyM1Model\":-1,\"creditOverdueMoneyM3Model\":-1,\"creditOverdueMoneyM6Model\":-1,\"creditOverdueMoneyM12Model\":-1,\"creditRepayCountM1Model\":4,\"creditRepayCountM3Model\":2,\"creditRepayCountM6Model\":9,\"creditRepayCountM12Model\":5,\"creditRepayMoneyM1Model\":6,\"creditRepayMoneyM3Model\":6,\"creditRepayMoneyM6Model\":3,\"creditRepayMoneyM12Model\":2,\"ratingsLoaningRaiseAmountStatus\":1,\"ratingsLoaningCreditTimeStatus\":2,\"ratingsSupplementLegalPersonExecutiveCompany\":null,\"ratingsSupplementShareholderInvestmentCompany\":null,\"ratingsLoaningQueryCreditMonthModel\":0}");
//        System.currentTimeMillis();


    }


    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}

@Data
class Model {
    Integer a;
    Integer b;

    Model(int a, int b) {
        this.a = a;
        this.b = b;
    }
}