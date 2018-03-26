class WordDictionary {
   private final double numberDocs;
   Corpus corpus;

   WordDictionary(double numberDocs, Corpus corpus) {
      this.numberDocs = numberDocs;
      this.corpus = corpus;
   }

   public double getNumberDocs() {
      return numberDocs;
   }

   public Corpus getCorpus() {
      return corpus;
   }
}
