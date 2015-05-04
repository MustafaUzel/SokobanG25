package exceptions;

public class GebruikersnaamInGebruikException extends RuntimeException
{
    public GebruikersnaamInGebruikException()
    {
    }
    
    public GebruikersnaamInGebruikException(String message)
    {
        super(message);
    }

    public GebruikersnaamInGebruikException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GebruikersnaamInGebruikException(Throwable cause)
    {
        super(cause);
    }

    public GebruikersnaamInGebruikException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
