public abstract class MovingThing extends Thing {
    double direction;
    float velocity;
    float angularVelocity;
    int RADIUS;
    int centerX;
    int centerY;

    MovingThing(int x, int y, float v, float aV, double d, int RADIUS ) {
        super(x, y);
        this.RADIUS = RADIUS;
        this.velocity = v;
        this.angularVelocity = aV;
        this.direction = d;
        this.centerX = x + RADIUS/2;
        this.centerY = y + RADIUS/2;
    }

    private void changeDirection(double amount) {
        this.direction = (this.direction + amount) % (2 * Math.PI);
    }

    public void turnLeft() {
        this.changeDirection(this.angularVelocity);
    }

    public void turnRight() {
        this.changeDirection(- this.angularVelocity);
    }

    void step() {
        this.x += this.velocity * Math.sin(this.direction) ;
        this.y += this.velocity * Math.cos(this.direction);
    }

    int getRADIUS(){return this.RADIUS;}

    void stepBack(){
        this.changeDirection(Math.PI);
        this.step();

        this.changeDirection(Math.PI);
    }

    double getDirection(){return this.direction;}

}