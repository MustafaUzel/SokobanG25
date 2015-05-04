package exceptions;

public class GebruikersException extends RuntimeException
{
    public GebruikersException()
    {
    }
    
    public GebruikersException(String message)
    {
        super(message);
    }

    public GebruikersException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikersException(Throwable cause)
    {
        super(cause);
    }

    public GebruikersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
