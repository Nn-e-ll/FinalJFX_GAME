module org.example.finalprojectjfx_game {
    requires javafx.controls;
   requires javafx.fxml;
   requires javafx.web;

   requires org.controlsfx.controls;
   requires com.dlsc.formsfx;
   requires net.synedra.validatorfx;
   requires org.kordamp.ikonli.javafx;
   requires org.kordamp.bootstrapfx.core;
   requires eu.hansolo.tilesfx;
   requires com.almasb.fxgl.all;

    opens org.example.finalprojectjfx_game to javafx.fxml;
    exports org.example.finalprojectjfx_game;
    exports org.example.finalprojectjfx_game.leaderbord;
    opens org.example.finalprojectjfx_game.leaderbord to javafx.fxml;
    exports org.example.finalprojectjfx_game.game;
    opens org.example.finalprojectjfx_game.game to javafx.fxml;
    exports org.example.finalprojectjfx_game.platform;
    opens org.example.finalprojectjfx_game.platform to javafx.fxml;
    exports org.example.finalprojectjfx_game.SketchLeap;
    opens org.example.finalprojectjfx_game.SketchLeap to javafx.fxml;
}

