package gutfud.dacs;

import gutfud.dacs.DTO.Messages;
import gutfud.dacs.DTO.User;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class MessageListCell extends ListCell<Messages> {
    private HBox content;
    private Text sender;
    private Text message;
    private Text timestamp;
    private User user;

    public MessageListCell(User user) {
        super();
        this.user = user;
        sender = new Text();
        message = new Text();
        timestamp = new Text();
        VBox vbox = new VBox(sender, message, timestamp);
        content = new HBox(vbox);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Messages item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            sender.setText(item.getSenderId());
            message.setText(item.getMessageText());
            timestamp.setText(String.valueOf(item.getTimeStamp()));
            System.out.println("from MessageListCell: Mess from" + item.getSenderId() + " Recive:" + user.getUserName());
            if (item.getSenderId().equalsIgnoreCase(user.getUserName())) {
                content.setAlignment(Pos.CENTER_RIGHT);
                sender.setStyle("-fx-fill: White; -fx-text-alignment: right; -fx-font-size: 22px ");
                message.setStyle("-fx-background-color: White; -fx-padding: 5px;");
            } else {
                content.setAlignment(Pos.CENTER_LEFT);
                sender.setStyle("-fx-fill: White; -fx-text-alignment: left; -fx-font-size: 22px ");
                message.setStyle("-fx-background-color: White; -fx-padding: 5px;");
            }
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}
