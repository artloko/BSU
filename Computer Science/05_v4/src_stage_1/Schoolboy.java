import java.io.Serializable;
import java.security.InvalidParameterException;

public class Schoolboy extends Learner implements Serializable {

    private static final long serialVersionUID = 1L;
    private int form;

    public Schoolboy(String name, Type type, int[] marks, int form)
    {
        super(name, type, marks);
        if (!validateYear(form))
            throw new InvalidParameterException("Wrong parameter: " + form);
        this.form = form;
    }

    private boolean validateYear(int form){
        return (form > 0 && form < 12);
    }

    public int getForm() {
        return form;
    }

    @Override
    public String toString() {
        return  super.toString() + " Schoolboy{" +
                form + "=" + form +
                "} ";
    }
}
