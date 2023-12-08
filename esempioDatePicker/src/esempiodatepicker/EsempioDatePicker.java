import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EsempioDatePicker extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione di un TextField esistente
        TextField textField = new TextField();

        // Creazione di un DatePicker personalizzato senza TextField incorporato
        DatePicker customDatePicker = new DatePicker();
        customDatePicker.getEditor().setDisable(true); // Disabilita il TextField incorporato

        // Creazione di un bottone personalizzato per aprire il DatePicker
        Button selectDateButton = new Button("Seleziona Data");
        selectDateButton.setOnAction(event -> {
            // Simula il clic sulla casella di testo del DatePicker
            customDatePicker.getEditor().fireEvent(event.copyFor(customDatePicker.getEditor(), customDatePicker.getEditor()));
        });

        // Aggiunta di un listener per gestire il cambiamento della data nel DatePicker
        customDatePicker.setOnAction(event -> {
            // Imposta il valore del TextField con la data selezionata
            textField.setText(customDatePicker.getValue().toString());
        });

        // Creazione di un HBox per organizzare i controlli
        HBox hbox = new HBox(10); // Spaziatura di 10 pixel tra i nodi
        hbox.setPadding(new Insets(10)); // Padding esterno

        // Aggiunta di TextField, bottone e DatePicker all'HBox
        hbox.getChildren().addAll(textField, selectDateButton);

        // Creazione della scena e impostazione della finestra principale
        Scene scene = new Scene(hbox, 300, 100);
        primaryStage.setTitle("Custom DatePicker Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
