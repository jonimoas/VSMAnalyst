disp('importing data')
tfidfsparseW = zeros(8017,11429);
querytermsparse = zeros(8017, 93);
termidf = zeros(8017,1);

fid = fopen('..\IO\VSM\query-term.txt', 'r');
row = 1;
while 1
  tline = fgetl(fid);
  if ~ischar(tline), break, end
  querytermsparse(row, :) = str2double(regexp(tline, ' ', 'split'));
  row = row + 1;
end
fclose(fid);

fid = fopen('..\IO\VSM\tfidf-sparse-W.txt', 'r');
row = 1;
while 1
  tline = fgetl(fid);
  if ~ischar(tline), break, end
  tfidfsparseW(row, :) = str2double(regexp(tline, ' ', 'split'));
  row = row + 1;
end
fclose(fid);

fid = fopen('..\IO\VSM\termIdf.txt', 'r');
row = 1;
while 1
  tline = fgetl(fid);
  if ~ischar(tline), break, end
  termidf(row) = str2double(tline);
  row = row + 1;
end
fclose(fid);