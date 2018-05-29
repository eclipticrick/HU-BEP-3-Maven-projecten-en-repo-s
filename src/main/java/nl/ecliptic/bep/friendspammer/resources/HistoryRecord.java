package nl.ecliptic.bep.friendspammer.resources;


public class HistoryRecord {
    private String to;
    private String from;
    private String subject;
    private String text;
    private boolean asHtml;

    public HistoryRecord() {
        // used to instantiate the object, use setters to give it values
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getAsHtml() {
        return asHtml;
    }

    public void setAsHtml(boolean asHtml) {
        this.asHtml = asHtml;
    }
}
