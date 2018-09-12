package com.kaipin.search;

public class Version implements Comparable<Version> {
	public static final Version UNKNOWN = new Version(0, 0, 0);
	Integer major;
	Integer minor;
	Integer patch;

	public Version(int major, int minor, int patch) {
		this.major = Integer.valueOf(major);
		this.minor = Integer.valueOf(minor);
		this.patch = Integer.valueOf(patch);
	}

	public int compareTo(Version o) {
		if (this.major != o.major) {
			return this.major.compareTo(o.major);
		}
		if (this.minor != o.minor) {
			return this.minor.compareTo(o.minor);
		}
		if (this.patch != o.patch) {
			return this.patch.compareTo(o.patch);
		}
		return 0;
	}

	public String toString() {
		return "" + this.major + "." + this.minor + "." + this.patch;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.major == null ? 0 : this.major.hashCode());
		result = 31 * result + (this.minor == null ? 0 : this.minor.hashCode());
		result = 31 * result + (this.patch == null ? 0 : this.patch.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Version)) {
			return false;
		}
		Version other = (Version) obj;
		if (this.major == null) {
			if (other.major != null)
				return false;
		} else if (!this.major.equals(other.major)) {
			return false;
		}
		if (this.minor == null) {
			if (other.minor != null)
				return false;
		} else if (!this.minor.equals(other.minor)) {
			return false;
		}
		if (this.patch == null) {
			if (other.patch != null)
				return false;
		} else if (!this.patch.equals(other.patch)) {
			return false;
		}
		return true;
	}
}