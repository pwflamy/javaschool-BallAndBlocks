package ru.javaschool.flamy;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.ListIterator;

public class World implements Constant {
    private ArrayList<Drawable> drawableObject = new ArrayList<>();
    private ArrayList<HavingNextStation> havingNextStations = new ArrayList<>();

    private ArrayList<Block> listBlocks = new ArrayList<>();

    private final Platform platform;
    private final Ball ball;
    private final Score score;

    public boolean gameOn = false;
    public boolean gameEnd = false;

    public World() {
        platform = new Platform(200,470, 0,0, 5);
        ball = new Ball(200+Constant.PLATFORM_WIDTH/2-BALL_SIZE/2,470-BALL_SIZE, 0, 0, 5);
        score = new Score(20,20);
        drawableObject.add(platform);
        drawableObject.add(ball);
        drawableObject.add(score);
        havingNextStations.add(platform);
        havingNextStations.add(ball);
        for (int i = 0; i < WINDOW_HEIGHT / 2 / (BLOCK_HEIGHT+10); i++)
            for (int j = 0; j < WINDOW_WIDTH / (BLOCK_WIDTH+20); j++) {
                Block block = new Block(20 + j * (BLOCK_WIDTH+20), 40 + i*(BLOCK_HEIGHT+10));
                listBlocks.add(block);
                drawableObject.add(block);
            }
    }

    public Platform getPlatform() {
        return platform;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Drawable> getDrawableObject() {
        return drawableObject;
    }

    public void startGame() {
        gameOn = true;
        ball.start();
    }

    public void newState() {
        if (gameOn) {
            Position oldBallPosition = new Position(ball.getPosition());
            havingNextStations.forEach(HavingNextStation::nextState);
            platformCollisions();
            ballCollisions(oldBallPosition);
        }
    }

    private void platformCollisions() {
        Position position = platform.getPosition();
        if (position.getCoord().getX() + platform.getWidth() > WINDOW_HEIGHT) position.setCoord(new Point2D(WINDOW_HEIGHT-platform.getWidth(), position.getCoord().getY()));
        if (position.getCoord().getX() < 0) position.setCoord(new Point2D(0, position.getCoord().getY()));
    }

    private void ballCollisions(Position oldPosition) {
        Position position = ball.getPosition();
        Point2D coord = position.getCoord();
        if (coord.getX()+ball.getSize() > WINDOW_WIDTH) {
            position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                    new Point2D(WINDOW_WIDTH-BALL_SIZE,0), new Point2D(WINDOW_WIDTH-BALL_SIZE, WINDOW_HEIGHT)));
            position.reflectDirection(VECTOR_LEFT);
        } else if (coord.getX() < 0) {
            position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                    new Point2D(0,0), new Point2D(0, WINDOW_HEIGHT)));
            position.reflectDirection(VECTOR_RIGHT);
        } else if (coord.getY() < 0) {
            position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                    new Point2D(0, 0), new Point2D(WINDOW_WIDTH, 0)));
            position.reflectDirection(VECTOR_DOWN);
        } else if (coord.getY() + BALL_SIZE > WINDOW_HEIGHT) {
            gameOn = false;
            gameEnd = true;
            ball.stop();
        }
        AABB myAABB = ball.getAABB();
        AABB platformAABB = platform.getAABB();
        if (coord.getY() > 470-BALL_SIZE && myAABB.testAABB(platformAABB)) {
            position.reflectDirection(myAABB.normalToAABB(platformAABB));
        }
        ListIterator<Block> li = listBlocks.listIterator();
        while (li.hasNext()) {
            Block block = li.next();
            if (myAABB.testAABB(block.getAabb())) {
                position.reflectDirection(myAABB.normalToAABB(block.getAabb()));
                score.addScore(10);
                li.remove();
                drawableObject.remove(block);
            }
        }
        if (listBlocks.isEmpty()) {
            gameEnd = true;
            gameOn = false;
            ball.stop();
        }
    }

    public void platformLeft() {
        platform.moveLeft();
    }

    public void platformRight() {
        platform.moveRight();
    }

    public void platformStop() {
        platform.moveStop();
    }
}

