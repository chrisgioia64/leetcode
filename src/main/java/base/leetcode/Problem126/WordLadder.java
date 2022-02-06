package base.leetcode.Problem126;

import java.util.*;

public class WordLadder {

    private Map<String, List<String>> neighborMap;
    private HashSet<String> wordSet;
//    private HashSet<String> seen;
    private String endWord;
    private List<StringRecord> endRecords;
    private Map<String, StringRecord> recordMap;
    private int count;

    private static class StringRecord {
        private String string;
        private List<StringRecord> lastNodes;

        public StringRecord(String string, StringRecord last) {
            this.string = string;
            this.lastNodes = new LinkedList<>();
            this.lastNodes.add(last);
        }

        public void add(StringRecord last) {
            this.lastNodes.add(last);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        neighborMap = new HashMap<>();
        wordSet = new HashSet<>(wordList);
//        wordSet.add(endWord);
        this.endWord = endWord;
        this.endRecords = new LinkedList<>();
        this.count = 0;
        this.recordMap = new HashMap<>();
//        System.out.println(wordSet.size());

        Queue<StringRecord> stack = new LinkedList<StringRecord>();
        stack.add(new StringRecord(beginWord, null));

        List<StringRecord> finalNodes = new LinkedList<>();
        int level = 0;

        while (!stack.isEmpty()) {
            int count = 0;
            int levelSize = stack.size();

            Set<String> levelNodes = new HashSet<>();
            while (count < levelSize) {
                count++;
                StringRecord node = stack.poll();
                if (node.string.equals(endWord)) {
                    finalNodes.add(node);
                }

                for (String neighbor : getNeighbors(node.string)) {
                    if (!this.recordMap.containsKey(neighbor)) {
                        StringRecord record = new StringRecord(neighbor, node);
                        stack.add(record);
                        levelNodes.add(record.string);
                        recordMap.put(record.string, record);
                    } else {
                        if (levelNodes.contains(neighbor)) {
                            StringRecord record = recordMap.get(neighbor);
                            record.lastNodes.add(node);
                            levelNodes.add(record.string);
                        } else {
                            // do nothing
                        }
                    }
                }
            }
            if (finalNodes.size() > 0) {
                break;
            }
            level++;
        }

        LinkedList<List<String>> result = new LinkedList<>();
        for (StringRecord finalNode : finalNodes) {
            LinkedList<String> current = new LinkedList<>();
            getPaths(finalNode, current, result);
        }
        return result;
    }

    public void getPaths(StringRecord node,
                                       LinkedList<String> current, List<List<String>> result) {
        if (node == null) {
            result.add(current);
        } else {
            if (node.lastNodes.size() == 1) {
                current.addFirst(node.string);
                getPaths(node.lastNodes.get(0), current, result);
            } else {
                for (StringRecord lastNode : node.lastNodes) {
                    LinkedList<String> newCurrent = new LinkedList<>(current);
                    newCurrent.addFirst(node.string);
                    getPaths(lastNode, newCurrent, result);
                }
            }
        }
    }


    public List<String> getNeighbors(String word) {
        List<String> result = new LinkedList<>();
        if (neighborMap.containsKey(word)) {
            return neighborMap.get(word);
        } else {
            List<String> neighborWords = new LinkedList<>();
            char[] currentWord = word.toCharArray();
            for (String neighbor : wordSet) {
                if (isAdjacent(currentWord, neighbor.toCharArray())) {
                    neighborWords.add(neighbor);
                }
            }
            neighborMap.put(word, neighborWords);
            return neighborWords;
        }
    }

    public boolean isAdjacent(char[] word1, char[] word2) {
        if (word1.length != word2.length) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < word1.length; i++) {
            if (word1[i] != word2[i]) {
                diff++;
            }
        }
        return diff == 1;
    }


    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        WordLadder ladder = new WordLadder();
        List<List<String>> result = ladder.findLadders("hit", "cog", wordList);
        System.out.println(result);

        wordList = Arrays.asList("ted","tex","red","tax","tad","den","rex","pee");
        result = ladder.findLadders("red", "tax", wordList);
        System.out.println(result);
//
        wordList = Arrays.asList("hot","dot","dog","lot","log");
        result = ladder.findLadders("hit", "cog", wordList);
        System.out.println(result);

        wordList = Arrays.asList("kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob");
        result = ladder.findLadders("cet", "ism", wordList);
        System.out.println(result);
    }

}
