import com.stantonj.WildMap.WildMap;

/**
 * Created by Joseph Stanton on 2/22/15.
 */
public class test1 {

    public static void main(String[] args){
        WildMap<Integer> test = new WildMap<Integer>();

        test.put("/test/1", 1);
        test.put("/test/2", 2);

        test.put("/test/*", -1);
        test.put("/test/", 0);
        test.put("/tes", 9);
        test.put("/tes*t/5", 10);
        test.put("/id-*/", 314);
        test.put("/name-*", 628);
        test.put("*/*", -2);


        System.out.println("/test/ => " + test.get("/test/"));
        System.out.println("/test/1 => " + test.get("/test/1"));
        System.out.println("/test/2 => " + test.get("/test/2"));
        System.out.println("/test/3 => " + test.get("/test/3") );
        System.out.println("/tes => " + test.get("/tes") );
        System.out.println("/tesqt/5 => " + test.get("/tesqt/5") );
        System.out.println("/tesqq/5 => " + test.get("/tesqq/5") );
        System.out.println("/test/5 => " + test.get("/test/5") );
        System.out.println();
        System.out.println("/id-1/ => " + test.get("/id-1/") );
        System.out.println("/id-2/ => " + test.get("/id-2/") );
        System.out.println("/name-1/ => " + test.get("/name-1/") );
        System.out.println("/name-2 => " + test.get("/name-2") );
        System.out.println("foo/bar => " + test.get("foo/bar") );
        System.out.println("*/bar => " + test.get("*/bar") );

    }
}
