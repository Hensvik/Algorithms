package base.class07;

//前缀树
//前缀树是N叉树的一种特殊形式。通常来说，一个前缀树是用来存储字符串的。前缀树的每一个节点代表一个字符串（前缀）。
// 每一个节点会有多个子节点，通往不同子节点的路径上有着不同的字符。子节点代表的字符串是由节点本身的原始字符串，以及通往该子节点路径上所有的字符组成的。
public class Code01_TrieTree {

	public static class TrieNode {
		//path个通过该节点的路径数量
		public int path;
		//end个字符串的结尾节点
		public int end;
		//记录后续节点？
		public TrieNode[] nexts;

		public TrieNode() {
			path = 0;
			end = 0;
			//准备了26条路代表26个字母，但是并不一定都有值，如果数量特别多，可以换成HashMap<Char,Node> nexts;
			//如果希望路与路之间有序组织则可以使用有序表TreeMap<Char,Node>nexts;
			nexts = new TrieNode[26];
		}
	}

	public static class Trie {
		//表示头结点，ps：根节点的path代表目前TrieNode数组的数量
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			//node从根节点出发
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {	//从左往右遍历字符
				//当前字符减ascii码，比如，a-'a'=0 c-'a'=2
				index = chs[i] - 'a';	//由字符，对应走哪一条路
				//如果node的后续节点为null，则新建节点，否则直接移动，同时path的计数++
				if (node.nexts[index] == null) {
					node.nexts[index] = new TrieNode();
				}
				node = node.nexts[index];
				node.path++;
			}
			node.end++;
		}

		//删除操作
		public void delete(String word) {
			//先确认树中是否加入过word
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				TrieNode node = root;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					//沿途path值不断--，如果遇到某个节点path减为0，则直接移除该节点
					if (--node.nexts[index].path == 0) {
						node.nexts[index] = null;
						return;
					}
					node = node.nexts[index];
				}
				node.end--;
			}
		}

		//查询word这个单词之前加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			//将word转变成数组
			char[] chs = word.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				//当遍历字符串过程中出现null，即不匹配时，返回0，值不存在
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			//如果存在，返回出现的次数
			return node.end;
		}

		//在所有加入的字符串中，有几个是以pre字符串为前缀的
		//基本没变化，返回的结果差异而已
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			TrieNode node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.path;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuo");
		trie.insert("zuo");
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.delete("zuo");
		System.out.println(trie.search("zuo"));
		trie.insert("zuoa");
		trie.insert("zuoac");
		trie.insert("zuoab");
		trie.insert("zuoad");
		trie.delete("zuoa");
		System.out.println(trie.search("zuoa"));
		System.out.println(trie.prefixNumber("zuo"));

	}

}
