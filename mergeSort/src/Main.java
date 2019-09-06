public class Main {

    public static void main(String[] args) {
        int[] A = {14, 7, 3, 12, 9, 11, 6, 2};
        int[] temp = new int[A.length];
        mergeSort(A, 0, A.length - 1, temp);

        for (int each: A){
            System.out.print(each + "   ");
        }
    }

    public static void mergeSort(int[] A, int start, int end, int[] temp) {
        if(start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int mid = (start + end) / 2;

        mergeSort(A, start, mid, temp);
        mergeSort(A, mid + 1, end, temp);
        merge(A, start, mid, end, temp);
    }

    public static void merge(int[] A, int start, int mid, int end, int[] temp) {
        int left = start;
        int right = mid + 1;
        int index = start;

        while(left <= mid && right <= end) {
            if(A[left] < A[right]) {
                temp[index++] = A[left++];
            } else {
                temp[index++] = A[right++];
            }
        }
        while(left <= mid) {
            temp[index++] = A[left++];
        }
        while (right <= end) {
            temp[index++] = A[right++];
        }
        for(index = start; index <= end; index++) {
            A[index] = temp[index];
        }
    }
}
