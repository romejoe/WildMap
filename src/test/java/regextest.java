/**
 * Created by Joey on 2/24/15.
 */
public class regextest {
    static public void main(String[] args){
        String[] tests = new String[]{"/test/*", "/name-*/", "*/*"};
        for(String test: tests){
            for(String result:test.split("((?<=[^\\\\])\\*)|(^\\*)")){
                System.out.println(result);
            }
            System.out.println();
        }
    }
}
