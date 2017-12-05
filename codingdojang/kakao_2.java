import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.Messaging.SyncScopeHelper;

public class kakao_2 {
	static int[] score = new int[3];
	static int j = 0;

	public static void main(String[] args) {
		String content = "1D*1S*1T*";
		/*
		예제	dartResult	answer	설명
		1	1S2D*3T	37	1^1 * 2 + 2^2 * 2 + 3^3
		2	1D2S#10S	9	1^2 + 2^1 * (-1) + 10^1
		3	1D2S0T	3	1^2 + 2^1 + 0^3
		4	1S*2T*3S	23	1^1 * 2 * 2 + 2^3 * 2 + 3^1
		5	1D#2S*3S	5	1^2 * (-1) * 2 + 2^1 * 2 + 3^1
		6	1T2D3D#	-4	1^3 + 2^2 + 3^2 * (-1)
		7	1D2S3T*	59	1^2 + 2^1 * 2 + 3^3 * 2
		*/
		
		System.out.println(content);
		int i = 0;
		Pattern groupPattern = Pattern.compile("(\\d*\\d*[a-zA-Z]+[#*]*)"); 
		Matcher groupMatcher = groupPattern.matcher(content);

		System.out.println("-------");
		while (groupMatcher.find()) {
			String name = groupMatcher.group(); // 첫번째 그룹에 일치한 문자열을 구함
			event(name);
		}
		int tot = 0;
		for (int k = 0; k < j; k++) {
			tot += score[k];
			if (k == j - 1) {
				System.out.print(score[k] + " = ");
			} else {
				System.out.print(score[k] + "+");
			}
		}
		System.out.println(tot);
	}

	public static void event(String str) {
		Pattern groupPattern = Pattern.compile("(\\d*\\d)*([a-zA-Z])+([#*])*"); 
		Matcher groupMatcher = groupPattern.matcher(str);
		int i = 0;
		groupMatcher.find();
		String a = (String) groupMatcher.group(2);
		int num = (groupMatcher.group(1) != null) ? Integer.parseInt(groupMatcher.group(1)) : 0;

		if (a.equals("S")) {
			num = (int) Math.pow(num, 1);
		} else if (a.equals("D")) {
			num = (int) Math.pow(num, 2);
		} else if (a.equals("T")) {
			num = (int) Math.pow(num, 3);
		}
		if (groupMatcher.group(3) != null) {
			num = groupMatcher.group(3).equals("*") ? num * 2 : num * -1;
			if (j != 0 && groupMatcher.group(3).equals("*")) {
				score[j - 1] = score[j - 1] * 2;
			}
		}
		score[j] = num;
		j++;
	}
}
