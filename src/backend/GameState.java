package backend;

import shared.GameSettings;

public class GameState
{
    GameSettings settings;
    //Other things like connected users etc

    public GameState(GameSettings settings)
    {
        this.settings = settings;
    }
}
