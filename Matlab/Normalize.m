disp('Normalizing')
for i = 1:size(tfidfsparseW,2)
    temp = tfidfsparseW(:,i);
    n = norm(temp,2);
    temp = temp./n;
    tfidfsparseW(:,i) = temp;
end
for i = 1:size(querytermsparse,2)
    temp = querytermsparse(:,i);
    n = norm(temp,2);
    temp = temp./n;
    querytermsparse(:,i) = temp;
end