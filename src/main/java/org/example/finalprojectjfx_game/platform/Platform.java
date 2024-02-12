package org.example.finalprojectjfx_game.platform;

import javafx.scene.shape.Rectangle;

public class Platform {
    private Rectangle Platform;

    public boolean WasUsed = false;

    private boolean CanBroken;

    private int JumpFores;

    public Platform(Rectangle Platform){

        this.Platform = Platform;
    }

    public Rectangle getPlatform(){return Platform;}

}
