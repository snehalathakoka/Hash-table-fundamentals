import java.util.*;

public class Problem4_PlagiarismDetector {

    // n-gram size
    private static final int N = 5;

    // Map: n-gram -> set of document names
    private HashMap<String, Set<String>> ngramMap = new HashMap<>();

    // Store documents
    private HashMap<String, List<String>> documentNgrams = new HashMap<>();

    // Generate n-grams
    private List<String> generateNgrams(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(words[i + j]).append(" ");
            }
            ngrams.add(sb.toString().trim());
        }
        return ngrams;
    }

    // Add document
    public void addDocument(String docName, String text) {

        List<String> ngrams = generateNgrams(text);
        documentNgrams.put(docName, ngrams);

        for (String gram : ngrams) {
            ngramMap.putIfAbsent(gram, new HashSet<>());
            ngramMap.get(gram).add(docName);
        }
    }

    // Compare documents
    public void compare(String doc1, String doc2) {

        List<String> list1 = documentNgrams.get(doc1);
        List<String> list2 = documentNgrams.get(doc2);

        int matchCount = 0;

        for (String gram : list1) {
            if (list2.contains(gram)) {
                matchCount++;
            }
        }

        double similarity = (matchCount * 100.0) / list1.size();

        System.out.println("Matching n-grams: " + matchCount);
        System.out.println("Similarity: " + similarity + "%");

        if (similarity > 50) {
            System.out.println("⚠️ PLAGIARISM DETECTED");
        }
    }

    public static void main(String[] args) {

        Problem4_PlagiarismDetector detector = new Problem4_PlagiarismDetector();

        String text1 = "this is a simple example of plagiarism detection system using n grams for comparison";
        String text2 = "this is a simple example of plagiarism detection system using n grams method";

        detector.addDocument("doc1", text1);
        detector.addDocument("doc2", text2);

        detector.compare("doc1", "doc2");
    }
}