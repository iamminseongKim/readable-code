package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.minesweeper.Minesweeper;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Advanced;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.ConsoleOutputHandler;

public class GameApplication {

    public static void main(String[] args) {


        GameConfig gameConfig = new GameConfig(
                new Advanced(),
                new ConsoleInputHandler(),
                new ConsoleOutputHandler()
        );

        Minesweeper minesweeper = new Minesweeper(gameConfig);
        minesweeper.initialize();
        minesweeper.run();
    }

    /**
     * DIP (Dependency Inversion Principle)
     *  - 고수준 모듈이 저수준 모듈을 참조하것 : 순방향
     *  - 고수준, 저수준 모듈이 모두 추상화에 의존해야 하는 것 : 역방향
     * DI (Dependency Injection)
     *  - 의존성 주입 (스프링)
     *  - "3"
     *  - 제 3자(스프링 컨텍스트) 가 나와 너의 의존성을 주입시켜준다.
     * IOC (Inversion of Control)
     *  - 제어의 역전 (역방향)
     *  - 프로그램의 흐름을 개발자가 아닌 프레임워크가 제어하도록 한다.
     *  - 제어가 순방향이라면 개발자가 프로그램의 흐름을 제어해야 한다.
     */
}