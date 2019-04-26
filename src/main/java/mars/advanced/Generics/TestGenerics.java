package mars.advanced.Generics;

import com.zhangys.entity.UserEntity;

public class TestGenerics {

    public static void main(String [] args) {

        try {
            System.out.println(Class.forName("com.zhangys.entity.UserEntity"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
