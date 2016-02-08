package com.klm.dev.exercises.devcase01.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DestinationListTest {

    //@formatter:off
    private String testSample =   "{\"destinations\":[                             "
            + "    {                                           "
            + "        \"origin\":\"BCN\",                     "
            + "        \"destination\":{                       "
            + "            \"code\":\"AMS\",                   "
            + "            \"name\":\"Amsterdam\",             "
            + "            \"description\":\"Amsterdam (AMS)\","
            + "            \"country\":\"NL\",                 "
            + "            \"continent\":\"EUR\",              "
            + "            \"coordinates\":{                   "
            + "                \"lat\":52.31667,               "
            + "                \"lon\":4.78417                 "
            + "            }                                   "
            + "        },                                      "
            + "        \"lowestFare\":{                        "
            + "            \"value\":99.0,                     "
            + "            \"currency\":\"EUR\"                "
            + "        }                                       "
            + "    },                                          "
            + "    {                                           "
            + "        \"origin\":\"BCN\",                     "
            + "        \"destination\":{                       "
            + "            \"code\":\"MIL\",                   "
            + "            \"name\":\"Milan\",                 "
            + "            \"description\":\"Milan (MIL)\",    "
            + "            \"country\":\"IT\",                 "
            + "            \"continent\":\"EUR\",              "
            + "            \"coordinates\":{                   "
            + "                \"lat\":45.46417,               "
            + "                \"lon\":9.20167                 "
            + "            }                                   "
            + "        },                                      "
            + "        \"lowestFare\":{                        "
            + "            \"value\":99.0,                     "
            + "            \"currency\":\"EUR\"                "
            + "        }                                       "
            + "    },                                          "
            + "    {                                           "
            + "        \"origin\":\"BCN\",                     "
            + "        \"destination\":{                       "
            + "            \"code\":\"LYS\",                   "
            + "            \"name\":\"Lyon\",                  "
            + "            \"description\":\"Lyon (LYS)\",     "
            + "            \"country\":\"FR\",                 "
            + "            \"continent\":\"EUR\",              "
            + "            \"coordinates\":{                   "
            + "                \"lat\":45.71611,               "
            + "                \"lon\":5.08194                 "
            + "            }                                   "
            + "        },                                      "
            + "        \"lowestFare\":{                        "
            + "            \"value\":120.0,                    "
            + "            \"currency\":\"EUR\"                "
            + "        }                                       "
            + "    },                                          "
            + "    {                                           "
            + "        \"origin\":\"BCN\",                     "
            + "        \"destination\":{                       "
            + "            \"code\":\"PAR\",                   "
            + "            \"name\":\"Paris\",                 "
            + "            \"description\":\"Paris (PAR)\",    "
            + "            \"country\":\"FR\",                 "
            + "            \"continent\":\"EUR\",              "
            + "            \"coordinates\":{                   "
            + "                \"lat\":48.85028,               "
            + "                \"lon\":2.40639                 "
            + "            }                                   "
            + "        },                                      "
            + "        \"lowestFare\":{                        "
            + "            \"value\":109.0,                    "
            + "            \"currency\":\"EUR\"                "
            + "        }                                       "
            + "    }                                           "
            + "]}                                              ";
    //@formatter:on


    @Test
    public void jsonToDestinationList() throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        DestinationList destinationList = mapper.readValue(testSample, DestinationList.class);

        Assert.assertNotNull(destinationList);

        Route route1 = destinationList.getDestinations().get(0);

        Assert.assertNotNull(route1);
        Assert.assertEquals(route1.getOrigin(), "BCN");
    }

}
