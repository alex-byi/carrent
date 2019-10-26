package by.htp.jd2.service.validation;

import by.htp.jd2.entity.Crash;

import java.util.List;

public class CrashDataValidator {
    private static final CrashDataValidator instance = new CrashDataValidator();

    private CrashDataValidator() {
    }

    public boolean checkCrashsList(List<Crash> list) {
        Crash trueCrash = new Crash();

        for (Crash crash : list) {
            if (crash.getClass() != trueCrash.getClass()) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCrashInfo(Crash crash) {
        return crash == null || crash.getDamage().isEmpty();
    }

    public static CrashDataValidator getInstance() {
        return instance;
    }
}
