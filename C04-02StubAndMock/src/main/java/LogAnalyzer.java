public class LogAnalyzer {
    private IWebService service;
    private IEmailService email;

    public LogAnalyzer(IWebService service, IEmailService email) {
        this.service = service;
        this.email = email;
    }

    public void analyze(String filename) {
        if (filename.length() < 8) {
            try {
                service.logError("Filename too short: " + filename);
            }
            catch (Exception e) {
                email.sendMail("admin@test.com", "fake exception", "can't log");
            }
        }
    }
}
