package lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao;

import lk.ijse.gdse71.serenitymentalhealththerapycentersystem.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory
                = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        PATIENT , USER , PAYMENT , THERAPIST , THERAPYSESSION , PROGRAM , REGISTRATION
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case PATIENT:
                return new PatientDAOImpl();
            case USER:
                return new UsersDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            case THERAPYSESSION:
                return new TherapySessionDAOImpl();
            case PROGRAM:
                return new TherapyProgramDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            default:
                return null;
        }
    }
}
