package mars.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class GuavaCacheUtil {

    private static Cache<Object,Object> dbCache = CacheBuilder.newBuilder()
            .maximumSize(2000)
            .expireAfterWrite(2, TimeUnit.MINUTES)
            .build();

    @SuppressWarnings("unchecked")
    public static <K,V> V getDbCache(K k,Callable<V> cable){
        try {
            return (V) dbCache.get(k, cable);
        } catch (Exception e) {
            System.out.println("get cache error.");
        }

        return null;
    }

    public static void main(String [] args) {
        GuavaCacheUtil.getDbCache(1, ()-> "111111");
    }

}
