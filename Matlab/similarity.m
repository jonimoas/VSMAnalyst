disp('Calculating Similarity')

resultsCos = zeros (size(querytermsparse,2),size(tfidfsparseW,2));
for i = 1:size(resultsCos)
    for j = 1:size(resultsCos,2)
        resultsCos(i,j) =  distCos(Q(i,:),V(j,:));
    end
end