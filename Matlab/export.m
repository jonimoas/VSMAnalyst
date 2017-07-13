disp('Exporting Results')


fid = fopen('../trec/vsmResultsTFW.txt', 'w');
for i = 1 : size(resultsCos)
    result = resultsCos(i,:);
    [result , resultindx] = sort(result,'descend');
    for j = 1 : 500
        S = [num2str(i),' 0 ',num2str(resultindx(j)), ' ',num2str(j), ' ', num2str(result(j)), ' mymethod\n'];
        fprintf(fid,S);
    end
end
fclose(fid);