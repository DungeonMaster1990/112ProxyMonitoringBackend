package ru.vtb.monitoring.vtb112.db.pg.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IncidentTest {

    private static final String URL = "http://url/test?param=1";

    @Test
    void getValidConferenceLinkFromMultiline() {
        Incident incident = new Incident();
        incident.setResolution("sometext#first find#\r\n#" + URL + "#\n#lastline#");
        Assertions.assertEquals(URL, incident.getConferenceLink());
    }

    @Test
    void getValidConferenceLinkFromSingleLine() {
        Incident incident = new Incident();
        incident.setResolution("#sometext##first find##" + URL + "#lastline#");
        Assertions.assertEquals(URL, incident.getConferenceLink());
    }

    @Test
    void getNullConferenceLinkFromInvalidResolution() {
        Incident incident = new Incident();
        incident.setResolution("test#first find#www.webex.com#lastline#");
        Assertions.assertNull(incident.getConferenceLink());
    }

    @Test
    void getNullWithNoSharp() {
        Incident incident = new Incident();
        incident.setResolution("test\r\nfirst find\r\n" + URL);
        Assertions.assertNull(incident.getConferenceLink());
    }
}