package magicGame.models.magics;

import magicGame.common.ExceptionMessages;

public abstract class MagicImpl implements Magic {
    private String name;
    private int bulletsCount;

    public MagicImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC_NAME);
        }
        this.name = name;
    }

    private void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGIC_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }

    @Override
    public int fire() {
        String className = this.getClass().getSimpleName();
        int firedBullets = 0;

        if (className.equals("RedMagic")) {
            firedBullets = 1;
        } else if (className.equals("BlackMagic")) {
            firedBullets = 10;
        }

        if ((this.bulletsCount - firedBullets) >= 0) {
            this.bulletsCount -= firedBullets;
            return firedBullets;
        } else {
            return 0;
        }
    }
}
