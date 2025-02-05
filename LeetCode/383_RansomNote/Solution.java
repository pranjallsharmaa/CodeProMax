class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        if(magazine.length() < ransomNote.length()) {
            return false;
        }

        int[] letters = new int[26];

        for(int i = 0; i < magazine.length(); i++) {
            letters[magazine.charAt(i) - 'a']++;
        }

        for(int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);
            if(letters[ch - 'a'] <= 0) {
                return false;
            }
            letters[ch - 'a']--;
        }
        return true;
    }
}