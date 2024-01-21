package com.oncolator.app.entity.responseEntity;

import java.util.List;

public class PhaseInfoResponse {
    public int id;
    public String phaseCode;
    public String phaseName;
    public String risk;
    public String randomisation;
    public int daysOfTherapy;
    public int startWeek;
    public int endWeek;
    public String phaseDescription;
    public int intrathecalInjections;
    public String possibleValues;
    public List<LittlePhaseInfo> availablePhasesList;
}
