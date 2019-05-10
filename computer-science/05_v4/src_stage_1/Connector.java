import java.io.*;

public class Connector {

    private String filename;

    public Connector(String filename)
    {
        this.filename = filename;
    }

    public void write(Learner[] learners) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeInt(learners.length);
            for(int i = 0; i < learners.length; i++) {
                objectOutputStream.writeObject(learners[i]);
            }
            objectOutputStream.flush();
        }
    }

    public Learner[] read() throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(filename);
        try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
            int length = objectInputStream.readInt();
            Learner[] learners = new Learner[length];
            for(int i = 0; i < length; i++){
                learners[i] = (Learner)objectInputStream.readObject();
            }
            return learners;
        }
    }
}
