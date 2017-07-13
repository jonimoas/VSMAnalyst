disp('Making Query-Idf matrix')
for i = 1:size(querytermsparse)
    for j = 1:size(querytermsparse,2)
        querytermsparse(i,j) = querytermsparse(i,j)*termidf(i);
    end
end