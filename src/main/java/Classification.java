public class Classification {

    private final float percentageLegitimate;

    public Classification(float percentageLegitimate) {
        this.percentageLegitimate = percentageLegitimate;
    }

    public Classification(boolean isLegitimate) {
        if (isLegitimate)
            percentageLegitimate = 100;
        else
            percentageLegitimate = 0;
    }

    public float getLikelihood() {
        return percentageLegitimate;
    }
}
