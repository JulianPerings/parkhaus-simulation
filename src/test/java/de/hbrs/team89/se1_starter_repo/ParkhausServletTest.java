package de.hbrs.team89.se1_starter_repo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;

public class ParkhausServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeAll
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }


}
