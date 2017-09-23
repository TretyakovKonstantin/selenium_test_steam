package framework.exceptions;

import java.io.PrintWriter;
import java.util.logging.Logger;

public class NotAllElementsHasBeenFoundException extends Exception {
    public NotAllElementsHasBeenFoundException(){
        super("Не все элементы были найдены");
    }
}
