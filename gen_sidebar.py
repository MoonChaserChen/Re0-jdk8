import os
from shutil import copyfile

suffix = ".md"
gen_file_name = "_sidebar.md"
doc_path = "docs"
read_me_file = "README.md"

gen_file_path = "/".join([doc_path, gen_file_name])
ignore_files = [gen_file_name, ".git", read_me_file]


def print_file(c_dir, depth, write2):
    for f in os.listdir(c_dir):
        re_f = os.path.join(c_dir, f)
        is_d = os.path.isdir(re_f)
        if is_d and f not in ignore_files:
            write2.write("\t" * depth + "- " + f + "\n")
            print_file(re_f, depth + 1, write2)
        else:
            if suffix in f and f not in ignore_files:
                f_n = os.path.splitext(f)[0]
                write2.write("\t" * depth + "- [" + f_n + "](/" + re_f + ")\n")


copyfile(read_me_file, "/".join([doc_path, read_me_file]))
with open(gen_file_path, 'w') as g_f:
    print_file(doc_path, 0, g_f)
