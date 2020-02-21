package main;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        Output output = Output.getInstance(args[0], args[1]);
        GameImplementation gameImplementation = new GameImplementation(gameInput, output);
        gameImplementation.implementGame();
    }
}
