public class Classification {

    private final float percentageLikelihood;

    public Classification(float percentageLikelihood) {
        this.percentageLikelihood = percentageLikelihood;
    }

    public float getLikelihood() {
        return percentageLikelihood;
    }
}
