import java.util.logging.Logger;
import java.util.logging.Level;


class LoggerClass {
    public static final Logger LOGGER = Logger.getLogger( LoggerClass.class.getName() );
    public static void LogException() {
        new Exception();
    }
}

class Test {
    public boolean getFeature(String feature) {
        return true;
    }

    public void thing() {
        if(!this.getFeature("http://apache.org/xml/features/disallow-doctype-decl")) {
            LoggerClass.LogException();
        }
        System.out.println("What!");

        
    }
}
