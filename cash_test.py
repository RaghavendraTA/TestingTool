import unittest
import cash

class coins_test(unittest.TestCase):

    def test_output_16(self):
        self.assertEqual(cash.coin_change(16, [10, 2, 5]), [2, 2, 2, 10])

    def test_output_17(self):
        self.assertEqual(cash.coin_change(17, [10, 2, 5]), [2, 5, 10])

    def test_output_14(self):
        self.assertEqual(cash.coin_change(14, [10, 1, 7]), [7, 7])

    def test_output_206(self):
        self.assertEqual(cash.coin_change(206, [10, 103, 200]), [103, 103])
    
    def test_output_empty(self):
        self.assertEqual(cash.coin_change(20, [6]), [])

if __name__ == '__main__':
    unittest.main()
