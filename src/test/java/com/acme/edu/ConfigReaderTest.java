package com.acme.edu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ConfigReaderTest implements SysoutCaptureAndAssertionAbility {
    private ConfigReader sut;

    @Before
    public void setUp() {
        sut = new ConfigReader();
        resetOut();
        captureSysout();
    }

    @Test
    public void shouldHandleFileNotFoundWhenTryingToGetHost() {
        sut.getHost();
        assertSysoutContains("Property file 'application.properties' not found");
    }

    @Test
    public void shouldHandleFileNotFoundWhenTryingToGetPort() {
        sut.getPort();
        assertSysoutContains("Property file 'application.properties' not found");
        assertSysoutContains("Cannot read port value from configuration file. Port set to default: 10000");
    }
}
