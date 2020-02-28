package ships;

import projectiles.DefenderProjectile;
import projectiles.Projectile;
import utils.Position;

public class ShooterShip extends InvaderShip {
  /**
   * Constructs a ShooterShip
   * @param p The initial position
   * @param armor The initial armor level
   */
	public ShooterShip(Position p, int armor) {
		super(p, armor);
	}

	public Projectile[] fire() {
		Projectile[] proj = new Projectile[1];
		proj[0] = new Projectile(this.getPosition(), 0.0, -PROJECTILE_SPEED, Projectile.GRAVITY);

		return proj;
	}

	@Override
	public String imgPath() {
		return "res/monster.png";

	}

	@Override
	public int getPoints() {
		return 50;
	}
}
