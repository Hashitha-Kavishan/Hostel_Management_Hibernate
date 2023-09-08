package bo;


import bo.impl.LoginBOImpl;
import bo.impl.ReserveBOImpl;
import bo.impl.RoomBOImpl;
import bo.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReserveBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        STUDENT, ROOM ,RESERVE,LOGIN
    }
}
