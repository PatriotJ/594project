import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.SentenceAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Lemmatizer {

    protected StanfordCoreNLP pipeline;

    public Lemmatizer() {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        // To create this class, we refer some of the public code 
    		// from stackOverFlow and github.
    	
    		Properties props;
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma");

        
        this.pipeline = new StanfordCoreNLP(props);
    }

    /**
     * Analysis the text String and convert word inside it to base form
     * @param documentText
     * @return List<String>
     */
    public List<String> lemmatize(String documentText)
    {
        List<String> lemmas = new ArrayList<String>();
        // Create an empty Annotation just with the given text
        Annotation document = new Annotation(documentText);
        // run all Annotators on this text
        this.pipeline.annotate(document);
        // Iterate over all of the sentences found
        List<CoreMap> sentences = document.get(SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                lemmas.add(token.get(LemmaAnnotation.class));
            }
        }
        return lemmas;
    }
}
