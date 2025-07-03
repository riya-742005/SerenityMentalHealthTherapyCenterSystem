package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        return (boFactory == null) ? boFactory
                = new BOFactory() : boFactory;
    }

    public enum BOTypes {
       PATIENT , PROGRAM , THERAPYSESSION , PAYMENT , USER , REGISTRATION , BOOKAPPOINTMENT , THERPIST, HOME
    }

    public SuperBO getBO(BOFactory.BOTypes boTypes) {
        switch (boTypes) {
            case PATIENT:
                return new PatientBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case THERAPYSESSION:
                return new TherapySessionBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case USER:
                return new UserBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case BOOKAPPOINTMENT:
                return new BookAppoinmentBOImpl();
            case THERPIST:
                return new TherapistBOImpl();
            case HOME:
                return new HomeBOImpl();
            default:
                return null;
        }
    }
}
