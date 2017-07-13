disp('Performing SVD')

k=200;

[U, S, V] = svds(tfidfsparseW,k);
Q = zeros(size(querytermsparse,2),k);
for i = 1:size(querytermsparse,2)
    Q(i,:) = querytermsparse(:,i)'*U;
end