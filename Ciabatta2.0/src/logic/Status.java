package logic;

public class Status {

	private String[] subject = { "left_speaker", "mixer", "right_speaker", "light", "hdd", "heather", "oth1", "oth2" };
	private Boolean[] value = { false, false, false, false, false, false, false, false };

	public Status() {
		// TODO Auto-generated constructor stub
	}

	public String get(String name) {
		for (int i = 0; i < this.subject.length; i++) {
			if (this.subject[i].equals(name)) {
				if (value[i]) {
					value[i] = !value[i];
					String subject = String.valueOf(i + 1);
					subject += "_";
					if (value[i]) {
						subject += String.valueOf(1);
					} else {
						subject += String.valueOf(0);
					}
					return subject;
				} else {
					value[i] = !value[i];
					String subject = String.valueOf(i + 1);
					subject += "_";
					if (value[i]) {
						subject += String.valueOf(1);
					} else {
						subject += String.valueOf(0);
					}
					return subject;
				}
			}
		}
		return null;
	}

}
